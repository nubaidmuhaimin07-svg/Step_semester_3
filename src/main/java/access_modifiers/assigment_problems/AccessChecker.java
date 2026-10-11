class LibraryMember {
    private String membershipPin;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    LibraryMember() {
    }
}

public class AccessChecker {
    static String classifyAccess(String fieldModifier, String accessorContext) {
        if (accessorContext.equals("SAME_CLASS")) {
            return "ALLOWED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        if (fieldModifier.equals("private")) {
            return "DENIED";
        }

        if (fieldModifier.equals("default")) {
            return accessorContext.equals("SAME_PACKAGE")
                    ? "ALLOWED" : "DENIED";
        }

        if (fieldModifier.equals("protected")) {
            if (accessorContext.equals("SAME_PACKAGE")
                    || accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {
                return "ALLOWED";
            }
            return "DENIED";
        }

        return "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {
        String[] modifiers = {"private", "default", "protected", "public"};
        String result = "";

        for (int i = 0; i < modifiers.length; i++) {
            int allowed = 0;
            int denied = 0;

            for (String[] attempt : attempts) {
                if (attempt[0].equals(modifiers[i])) {
                    if (classifyAccess(attempt[0], attempt[1]).equals("ALLOWED")) {
                        allowed++;
                    } else {
                        denied++;
                    }
                }
            }

            if (i > 0) result += " | ";
            result += modifiers[i] + ": " + allowed + " allowed / " + denied + " denied";
        }

        return result;
    }

    static String firstDeniedAttempt(String[][] attempts) {
        for (int i = 0; i < attempts.length; i++) {
            if (classifyAccess(attempts[i][0], attempts[i][1]).equals("DENIED")) {
                return attempts[i][0] + " via " + attempts[i][1]
                        + " (attempt #" + (i + 1) + ")";
            }
        }
        return "None Denied";
    }

    public static void main(String[] args) {
        String[][] batch = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(summarizeByModifier(batch));

        String[][] subclassAttempts = {
            {"public", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };

        System.out.println(firstDeniedAttempt(subclassAttempts));
    }
}
