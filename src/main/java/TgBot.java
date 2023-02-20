import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

public class TgBot extends TelegramLongPollingBot {

    final private String botUsername = "my_447_bot";
    final private String botToken = "5919924063:AAGTXiLlfxl2cKlCe8E31QF66ANOqjgJqWY";

    ReplyKeyboardMarkup replyKeyboardMarkup;
    //TicTacToeTg tttCurrent;
    StorageTgBot storage;
    public TgBot(/*TicTacToeTg game*/) {
        replyKeyboardMarkup = initReplyKeyboard();
        //tttCurrent = game;
        storage = new StorageTgBot();
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {

        try {
            SendMessage mes = new SendMessage();
            Message message = update.getMessage();
            Long id = message.getChatId();
            mes.setChatId(id);


            //  //  //  //  //  //  //  //  //  //

            if (storage.findGameById(id) == null) {
                storage.addGame(id, new TicTacToeTg());
            }
            TicTacToeTg tttCurrent = storage.findGameById(id);

            //  //  //  //  //  //  //  //  //  //


            String strParse = parseMessage(message.getText(), tttCurrent);

            String possible_result = null;
            if (!tttCurrent.getIsXturn()) {
                possible_result = tttCurrent.process();
            }
            if (possible_result.equals("O win")
                    || possible_result.equals("X win")
                    || possible_result.equals("draw!")) {
                possible_result = "\n\nRESULT: " + possible_result;
            } else {
                possible_result = "";
            }


            mes.setText("Your chatId="+ message.getChatId()
                    + "\n\nYour last step was:\n" + message.getText()
                    //+ "\n\nparsing: " + strParse
                    + possible_result
            + "\n\n\nCURRENT FIELD: \n"
            + printField(tttCurrent.field));


            mes.setReplyMarkup(replyKeyboardMarkup);
            execute(mes);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }


    public void notification(long chatId, String mes){
        try {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(mes);
            execute(sendMessage);
        } catch(TelegramApiException e) {
            e.printStackTrace();
        }
    }


    ReplyKeyboardMarkup initReplyKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow keyboardRow1 = new KeyboardRow();
        KeyboardRow keyboardRow2 = new KeyboardRow();
        KeyboardRow keyboardRow3 = new KeyboardRow();

        keyboardRows.add(keyboardRow1);
        keyboardRows.add(keyboardRow2);
        keyboardRows.add(keyboardRow3);

        keyboardRow1.add(new KeyboardButton("1_1"));
        keyboardRow1.add(new KeyboardButton("1_2"));
        keyboardRow1.add(new KeyboardButton("1_3"));

        keyboardRow2.add(new KeyboardButton("2_1"));
        keyboardRow2.add(new KeyboardButton("2_2"));
        keyboardRow2.add(new KeyboardButton("2_3"));

        keyboardRow3.add(new KeyboardButton("3_1"));
        keyboardRow3.add(new KeyboardButton("3_2"));
        keyboardRow3.add(new KeyboardButton("3_3"));

        replyKeyboardMarkup.setKeyboard(keyboardRows);

        return replyKeyboardMarkup;
    }



    public String parseMessage(String textMsg, TicTacToeTg tttCurrent) {

        if(textMsg.equals("/start")) {
            return "hi, leather bastard";
        }

        switch (textMsg) {
            case "1_1":
                if (tttCurrent.field[0][0] == 0) {
                    tttCurrent.field[0][0] = 10;
                    tttCurrent.setIsXturn(!(tttCurrent.getIsXturn()));
                    return "_1_1_";
                }
                break;
            case "1_2":
                if (tttCurrent.field[1][0] == 0) {
                    tttCurrent.field[1][0] = 10;
                    tttCurrent.setIsXturn(!(tttCurrent.getIsXturn()));
                    return "_1_2_";
                }
                break;
            case "1_3":
                if (tttCurrent.field[2][0] == 0) {
                    tttCurrent.field[2][0] = 10;
                    tttCurrent.setIsXturn(!(tttCurrent.getIsXturn()));
                    return "_1_3_";
                }
                break;
            case "2_1":
                if (tttCurrent.field[0][1] == 0) {
                    tttCurrent.field[0][1] = 10;
                    tttCurrent.setIsXturn(!(tttCurrent.getIsXturn()));
                    return "_2_1_";
                }
                break;
            case "2_2":
                if (tttCurrent.field[1][1] == 0) {
                    tttCurrent.field[1][1] = 10;
                    tttCurrent.setIsXturn(!(tttCurrent.getIsXturn()));
                    return "_2_2_";
                }
                break;
            case "2_3":
                if (tttCurrent.field[2][1] == 0) {
                    tttCurrent.field[2][1] = 10;
                    tttCurrent.setIsXturn(!(tttCurrent.getIsXturn()));
                    return "_2_3_";
                }
                break;
            case "3_1":
                if (tttCurrent.field[0][2] == 0) {
                    tttCurrent.field[0][2] = 10;
                    tttCurrent.setIsXturn(!(tttCurrent.getIsXturn()));
                    return "_3_1_";
                }
                break;
            case "3_2":
                if (tttCurrent.field[1][2] == 0) {
                    tttCurrent.field[1][2] = 10;
                    tttCurrent.setIsXturn(!(tttCurrent.getIsXturn()));
                    return "_3_2_";
                }
                break;
            case "3_3":
                if (tttCurrent.field[2][2] == 0) {
                    tttCurrent.field[2][2] = 10;
                    tttCurrent.setIsXturn(!(tttCurrent.getIsXturn()));
                    return "_3_3_";
                }
                break;
        }

        return "message not recognized";
    }

    public String printField(int[][] field) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            switch (field[i][0]) {
                case 10:
                    result.append(" X ");
                    break;
                case 200:
                    result.append(" O ");
                    break;
                default:
                    result.append("[   ]");
                    break;
            }
        }
        result.append("\n");
        for (int i = 0; i < 3; i++) {
            switch (field[i][1]) {
                case 10 -> result.append(" X ");
                case 200 -> result.append(" O ");
                default -> result.append("[   ]");
            }
        }
        result.append("\n");
        for (int i = 0; i < 3; i++) {
            switch (field[i][2]) {
                case 10 -> result.append(" X ");
                case 200 -> result.append(" O ");
                default -> result.append("[   ]");
            }
        }
        return String.valueOf(result);
    }


}
