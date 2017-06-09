import java.io.*;

public abstract class FileHandle {
    protected char[] inf;//сюда считаем входной файл
    protected String answer;//сюда запишем ответ
    protected int MIN_SYMBOLS = 4;

    public void pack(String input, String output) {
        File in = new File(input);
        File out = new File(output);
        if (in.exists()) {//если сущществует сделали
            read(in);//вызвали метод
            handle();//обработали
            if (output.isEmpty()) {
                write(new File(genOutName(in)));
            } else write(out);//вызвали метод
        } else {
            System.out.println("Input file not found");//если не нашли вывели ошибку
        }
    }

    private void read(File in) {
        try (FileReader input = new FileReader(in)) {
            long length = in.length();
            inf = new char[(int) length];
            input.read(inf);
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void write(File out) {
        try (FileWriter output = new FileWriter(out)) {
            output.write(answer);
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String genOutName(File input) {
        return "out" + input.getName();
    }

    abstract protected void handle();
}
