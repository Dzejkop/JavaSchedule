package IO;

/**
 * Created by Jakobs on 2015-10-26.
 */
public class UserInterface {

    InputReader reader;

    public UserInterface(InputReader reader) {
        this.reader = reader;
    }

    public int chooseInteger(String promt) {
        System.out.println(promt);
        return reader.readInt();
    }

    public int chooseOption(String promt, String[] options) {
        System.out.println(promt);

        int c = options.length;

        for(int i = 0 ; i < c; i++) {
            System.out.println("" + i + ". " + options[i]);
        }

        int ans = reader.readInt();

        if(ans >= c) return c-1;

        return ans;
    }

    public boolean yesOrNo(String question) {
        System.out.println(question + " (y/n)");

        String answer = reader.readString();

        if(answer.toLowerCase().startsWith("y")) {
            return true;
        }

        return false;
    }
}
