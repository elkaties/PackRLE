public class FileEncoder extends FileHandle {
    @Override
    protected void handle() {

        char currentSymbol = inf.length != 0 ? inf[0] : ' ';
        StringBuilder result = new StringBuilder();
        result.append(currentSymbol);
        int counter = 0;
        boolean isLastSymbol = false;
        for (int i = 1; i < inf.length; i++) {
            if (i == inf.length - 1) isLastSymbol = true;
            if (inf[i] != currentSymbol | isLastSymbol) {
                if (counter >= MIN_SYMBOLS) {
                    if (isLastSymbol) counter++;
                    result.append("[");
                    result.append(++counter);
                    result.append("]");

                    if (!isLastSymbol) result.append(inf[i]);
                    counter = 0;
                    currentSymbol = inf[i];
                } else {
                    for (int j = 0; j < counter; j++) {
                        result.append(currentSymbol);
                    }
                    counter = 0;
                    result.append(inf[i]);
                    currentSymbol = inf[i];
                }
            } else {
                counter++;
            }
        }
        answer = result.toString();
    }
}
