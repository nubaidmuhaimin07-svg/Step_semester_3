class LibraryMember {
    private String membershipPin;
    String branchCode;
    protected double finesOwed;
    public String displayName;
}
public class AccessChecker {
    static String classifyAccess(String modifier, String context) {
        if (modifier.equals("private")) {
            if (context.equals("SAME_CLASS")) {
                return "ALLOWED";
            }
            return "DENIED";
        }
        if (modifier.equals("default")) {
            if (context.equals("SAME_CLASS")
                    || context.equals("SAME_PACKAGE")) {
                return "ALLOWED";
            }
            return "DENIED";
        }
        if (modifier.equals("protected")) {
            if (context.equals("SAME_CLASS")
                    || context.equals("SAME_PACKAGE")
                    || context.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {
                return "ALLOWED";
            }
            return "DENIED";
        }
        if (modifier.equals("public")) {
            return "ALLOWED";
        }
        return "DENIED";
    }
    static String summarizeByModifier(String[][] attempts) {
        int privateAllowed = 0;
        int privateDenied = 0;
        int defaultAllowed = 0;
        int defaultDenied = 0;
        int protectedAllowed = 0;
        int protectedDenied = 0;
        int publicAllowed = 0;
        int publicDenied = 0;
        for (String[] attempt : attempts) {
            String result = classifyAccess(
                    attempt[0], attempt[1]);
            if (attempt[0].equals("private")) {
                if (result.equals("ALLOWED")) {
                    privateAllowed++;
                } else {
                    privateDenied++;
                }
            }
            if (attempt[0].equals("default")) {
                if (result.equals("ALLOWED")) {
                    defaultAllowed++;
                } else {
                    defaultDenied++;
                }
            }
            if (attempt[0].equals("protected")) {
                if (result.equals("ALLOWED")) {
                    protectedAllowed++;
                } else {
                    protectedDenied++;
                }
            }
            if (attempt[0].equals("public")) {
                if (result.equals("ALLOWED")) {
                    publicAllowed++;
                } else {
                    publicDenied++;
                }
            }
        }
        return "private: " + privateAllowed + " allowed / "
                + privateDenied + " denied | default: "
                + defaultAllowed + " allowed / "
                + defaultDenied + " denied | protected: "
                + protectedAllowed + " allowed / "
                + protectedDenied + " denied | public: "
                + publicAllowed + " allowed / "
                + publicDenied + " denied";
    }

    static String firstDeniedAttempt(String[][] attempts) {
        for (int i = 0; i < attempts.length; i++) {
            String result = classifyAccess(
                    attempts[i][0],
                    attempts[i][1]);

            if (result.equals("DENIED")) {
                return attempts[i][0]
                        + " via "
                        + attempts[i][1]
                        + " (attempt #" + (i + 1) + ")";
            }
        }

        return "None Denied";
    }
    public static void main(String[] args) {
        System.out.println(
                classifyAccess("private", "SAME_CLASS"));
        String[][] attempts = {
                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"protected", "SAME_PACKAGE"},
                {"protected", "SAME_CLASS"},
                {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println(
                summarizeByModifier(attempts));
        String[][] deniedAttempts = {
                {"public", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
                {"protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
                {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };

        System.out.println(
                firstDeniedAttempt(deniedAttempts));
    }
}
