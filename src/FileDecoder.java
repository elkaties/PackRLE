public class FileDecoder extends FileHandle {
    @Override
    protected void handle() {
        StringBuilder result = new StringBuilder("");
        boolean findOpenBracket = false;
        StringBuilder count = new StringBuilder();
        StringBuilder subString = new StringBuilder();

        /*
        currentSymbol - символ - претенднт на то, чтобы быть размноженным
        Ниже используется тернарный оператор. Если файл был пустым, значит
        длина массива "inf" будет равна нулю, в таком случае задаем currentSymbol = ' ',
        иначе берем первый элемент из массива. Далее в цикле мы будем менять значение currentSymbol
         */
        char currentSymbol = inf.length != 0 ? inf[0] : ' ';

        /*
        В цикле перебираем все символы из массива inf.
        Цикл делится на 3 условные части:
        1 часть.    Если findOpenBracket (обнаружен символ "["), который указывает на то,
                    что дальше будут число повторов предыдущего сивмола. Если он был обнаружен
                    на предыдущей итареции, значит сейчас должно идти число;
                    Делаем проверку:
                    является ли символ числом, если да, то добавляем его в count (StringBuilder числа),
                    если нет, то проверяем следующее условие.

                    Смотрим попалась ли нам закрывающая скобка, если да, то проверяем были ли до нее
                    числа, для этого достаточно проверить лишь длину count, если чисел не было,
                    то мы просто добавляем в result substring и идем дальше. Если были то
                    размножаем символ, который записан в переменную currentSymbol. Для этого
                    есть цикл. В конце условия на равность "[" очищаем count и subString.

                    Далее, если закрывающей скобки не было. Значит там какой-то другой символ
                    и это не относится к закодированной части, следовательно нужно печатать как есть.
                    Засовываем count в subString, а сам SubString в result, далее очищаем их и делаем
                    findOpenBracket = false.

         2 часть.   Проверка на нахождение открывающей скобки. Добавляем ее в subString и делаем
                    findOpenBracket true, чтобы далее запустить 1 часть.

         3 часть.   Если открывающая скобка не обнаружена, значит нужно сделать текущий символ
                    претендентом на размножение.

         UPD:   Добавлено условие isSmallNumber на случай, если в скобках окажется число, которое
                меньше или равно 4 (MIN_SYMBOLS), так как encoder не сворачивает 4 символа,
                то их и ен надо разворачивать.
         */
        for (char c : inf) {
            if (findOpenBracket) {
                if (c >= '0' & c <= '9') {
                    count.append(c);
                } else if (c == ']') {
                    boolean isSmallNumber = false;
                    if (count.length() != 0) {
                        if (Integer.valueOf(count.toString()) <= MIN_SYMBOLS) isSmallNumber = true;
                    }
                    if (count.length() == 0 | isSmallNumber) {
                        subString.append(count);
                        result.append(subString);
                        findOpenBracket = false;
                    } else {
                        //Если все хорошо и мы обнаружили открывающую и закрывающие скобки
                        //и между ними оказались числа
                        for (int i = 0; i < Integer.valueOf(count.toString()) - 1; i++) {
                            result.append(currentSymbol);
                        }
                    }
                    clear(count);
                    clear(subString);
                } else {
                    subString.append(count);
                    result.append(subString);
                    clear(subString);
                    clear(count);
                    if (c != '[') findOpenBracket = false;
                }
            }

            if (c == '[') {
                subString.append(c);
                if (findOpenBracket) currentSymbol = c;
                findOpenBracket = true;
            }
            if (!findOpenBracket) {
                currentSymbol = c;
                result.append(c);
            }
        }
        result.append(subString);
        answer = result.toString();
    }

    private void clear(StringBuilder stringBuilder) {
        stringBuilder.delete(0, stringBuilder.length());
    }
}
