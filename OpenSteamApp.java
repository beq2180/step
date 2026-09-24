import java.awt.Desktop;
import java.io.File;
import java.net.URI;

public class OpenSteamApp {
    public static void main(String[] args) {
        try {
            // Method 1: Using the Steam URI protocol (Recommended & OS-independent)
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                System.out.println("Opening Steam via URI protocol...");
                Desktop.getDesktop().browse(new URI("steam://"));
                return;
            }
        } catch (Exception e) {
            System.out.println("URI launch failed, attempting fallback process execution...");
        }

        // Method 2: Fallback to launching the executable directly based on OS
        try {
            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder pb = null;

            if (os.contains("win")) {
                // Default Windows path for Steam
                pb = new ProcessBuilder("C:\\Program Files (x86)\\Steam\\steam.exe");
            } else if (os.contains("mac")) {
                // Default macOS path for Steam
                pb = new ProcessBuilder("open", "-a", "Steam");
            } else if (os.contains("nix") || os.contains("nux")) {
                // Linux command assuming steam is in PATH
                pb = new ProcessBuilder("steam");
            }

            if (pb != null) {
                pb.start();
                System.out.println("Opened Steam via ProcessBuilder.");
            } else {
                System.err.println("Unsupported operating system.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
