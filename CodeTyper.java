import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.logging.*;
import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class CodeTyper implements NativeKeyListener {

    private static Robot robot;
    private static boolean isTyping = false;

    private static final String code = """
The Smart Attendance System is an automated solution designed to streamline and secure the process of recording attendance using Machine Learning and face recognition technology. Traditional attendance methods, such as manual roll calls or sign-ins, are time-consuming, prone to human error, and susceptible to proxy attendance. To address these issues, the proposed system employs a camera to capture real-time images of students, which are then processed using the OpenCV library and a trained face recognition model.

The system extracts facial features, compares them with stored data, and marks attendance automatically in a database, eliminating the need for manual intervention. The use of Machine Learning ensures high accuracy in face identification, even under varying lighting conditions or facial expressions. The recorded data can be retrieved instantly and visualized in the form of reports and graphs through a web-based dashboard.

This approach not only saves time but also enhances security, accuracy, and efficiency in attendance management. The system can be deployed in educational institutions, workplaces, and events, and has the potential for further integration with cloud storage and mobile notifications.
""";

    public static void main(String[] args) throws Exception {
        robot = new Robot();
        robot.setAutoDelay(50);

        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        logger.setUseParentHandlers(false);

        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(new CodeTyper());

        System.out.println("🟢 Press F8 to type, ESC to stop.");
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        if (e.getKeyCode() == NativeKeyEvent.VC_F8 && !isTyping) {
            isTyping = true;
            new Thread(() -> {
                int typedLines = typeSmartCode(code);
                cleanUpTrailingLines(typedLines);
            }).start();
        } else if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
                System.exit(0);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override public void nativeKeyReleased(NativeKeyEvent e) {}
    @Override public void nativeKeyTyped(NativeKeyEvent e) {}

    private int typeSmartCode(String code) {
        String[] lines = code.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].stripLeading();
            typeLine(line);
            if (i != lines.length - 1) {
                pressKey(KeyEvent.VK_ENTER);
                sleep(100);
            }
        }
        System.out.println("✅ Done typing.");
        return lines.length;
    }

    private void cleanUpTrailingLines(int linesToCheck) {
        pressKey(KeyEvent.VK_END);
        sleep(100);
        holdKey(KeyEvent.VK_SHIFT);
        for (int i = 0; i < linesToCheck * 3; i++) {
            pressKey(KeyEvent.VK_DOWN);
        }
        releaseKey(KeyEvent.VK_SHIFT);
        pressKey(KeyEvent.VK_BACK_SPACE);
        isTyping = false;
    }

    private void typeLine(String line) {
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            switch (c) {
                case '(' -> typeShifted(KeyEvent.VK_9);
                case ')' -> typeShifted(KeyEvent.VK_0);
                case '{' -> typeShifted(KeyEvent.VK_OPEN_BRACKET);
                case '}' -> typeShifted(KeyEvent.VK_CLOSE_BRACKET);
                case '[' -> pressKey(KeyEvent.VK_OPEN_BRACKET);
                case ']' -> pressKey(KeyEvent.VK_CLOSE_BRACKET);
                case '"' -> typeShifted(KeyEvent.VK_QUOTE);
                case '\'' -> pressKey(KeyEvent.VK_QUOTE);
                case ';' -> pressKey(KeyEvent.VK_SEMICOLON);
                case ' ' -> pressKey(KeyEvent.VK_SPACE);
                case ',' -> pressKey(KeyEvent.VK_COMMA);
                case '.' -> pressKey(KeyEvent.VK_PERIOD);
                case '=' -> pressKey(KeyEvent.VK_EQUALS);
                case ':' -> typeShifted(KeyEvent.VK_SEMICOLON);
                case '<' -> typeShifted(KeyEvent.VK_COMMA);
                case '>' -> typeShifted(KeyEvent.VK_PERIOD);
                case '!' -> typeShifted(KeyEvent.VK_1);
                case '@' -> typeShifted(KeyEvent.VK_2);
                case '#' -> typeShifted(KeyEvent.VK_3);
                case '$' -> typeShifted(KeyEvent.VK_4);
                case '%' -> typeShifted(KeyEvent.VK_5);
                case '^' -> typeShifted(KeyEvent.VK_6);
                case '&' -> typeShifted(KeyEvent.VK_7);
                case '*' -> typeShifted(KeyEvent.VK_8);
                case '+' -> typeShifted(KeyEvent.VK_EQUALS);
                case '_' -> typeShifted(KeyEvent.VK_MINUS);
                case '-' -> pressKey(KeyEvent.VK_MINUS);
                case '\\' -> pressKey(KeyEvent.VK_BACK_SLASH);
                case '/' -> pressKey(KeyEvent.VK_SLASH);
                case '|' -> typeShifted(KeyEvent.VK_BACK_SLASH);
                case '~' -> typeShifted(KeyEvent.VK_BACK_QUOTE);
                case '`' -> pressKey(KeyEvent.VK_BACK_QUOTE);
                default -> typeCharSmart(c);
            }
            sleep(50);
        }
    }

    private void typeCharSmart(char c) {
        try {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (keyCode == KeyEvent.CHAR_UNDEFINED) {
                System.err.println("⚠️ Can't type: " + c);
                return;
            }
            boolean isUpper = Character.isUpperCase(c);
            boolean requiresShift = isUpper || isShiftRequired(c);
            if (requiresShift) robot.keyPress(KeyEvent.VK_SHIFT);
            pressKey(keyCode);
            if (requiresShift) robot.keyRelease(KeyEvent.VK_SHIFT);
        } catch (Exception e) {
            System.err.println("❌ Error typing: " + c + " → " + e.getMessage());
        }
    }

    private boolean isShiftRequired(char c) {
        return "~!@#$%^&*()_+{}|:\"<>?".indexOf(c) != -1;
    }

    private void typeShifted(int keyCode) {
        robot.keyPress(KeyEvent.VK_SHIFT);
        pressKey(keyCode);
        robot.keyRelease(KeyEvent.VK_SHIFT);
    }

    private void pressKey(int keyCode) {
        robot.keyPress(keyCode);
        robot.keyRelease(keyCode);
    }

    private void holdKey(int keyCode) {
        robot.keyPress(keyCode);
    }

    private void releaseKey(int keyCode) {
        robot.keyRelease(keyCode);
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
