package pt.ual.android.bhjencryption.utils.cipher;

import android.view.textservice.SentenceSuggestionsInfo;
import android.view.textservice.SpellCheckerSession;
import android.view.textservice.SuggestionsInfo;
import android.view.textservice.TextInfo;
import android.view.textservice.TextServicesManager;

import pt.ual.android.bhjencryption.ui.utils.StringUtils;

import static androidx.core.content.ContextCompat.getSystemService;

public class VowelsByPointsCipher extends Cipher implements SpellCheckerSession.SpellCheckerSessionListener {
    String correctedString;

    public VowelsByPointsCipher(String message){ super(message); }

    @Override
    public CipherValidationResult validateEncrypt() {
        CipherValidationResult result = this.validate();

        if(!result.hasErrors()){
            if(!StringUtils.matchingChars(getMessage(), CipherUtils.ALPHABET_LOWER_AND_NUMERIC, true, false)){
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));
            }
        }

        return result;
    }

    @Override
    public CipherValidationResult validateDecrypt() {
        CipherValidationResult result = this.validate();

        if(!result.hasErrors()){
            if(!StringUtils.matchingChars(getMessage(), CipherUtils.ASCII_CONSONANTS_LOWER_NUMERIC_AND_POINT, true, false)){
                return new CipherResult(new CipherErrorCode(CipherErrorCode.MESSAGE_HAS_NOT_ALLOWED_CHARS));
            }
        }

        return result;
    }

    @Override
    public CipherResult encrypt() {
        return new CipherResult(encrypt(getMessage()));
    }

    @Override
    public CipherResult decrypt() {
        return new CipherResult(decrypt(getMessage()));
    }

    public static String encrypt(String enc){
        StringBuilder encoded = new StringBuilder();

        String[] palavras = enc.split(" ");

        for(String pal : palavras){
            for(char let : pal.toCharArray()){
                for(char vow : CipherUtils.VOWELS_LOWER.toCharArray()){
                    if(Character.toLowerCase(let) == vow || let == Character.toUpperCase(vow)){
                        encoded.append('.');
                        break;
                    }
                    else if(let == '.'){
                        break;
                    }
                    else{
                        encoded.append(let);
                        break;
                    }
                }
            }

            encoded.append(" ");
        }

        return encoded.toString();
    }

    public String decrypt(String enc){
        StringBuilder decoded = new StringBuilder();

        String[] palavras = enc.split(" ");

        /**
         *  TODO:
         *  - https://developer.android.com/guide/topics/text/spell-checker-framework ,
         *  - https://code.tutsplus.com/tutorials/an-introduction-to-androids-spelling-checker-framework--cms-23754
         */

        for(String i : palavras){
            String input = i.replace(".", "");
            fetchSuggestionsFor(input);
            decoded.append(correctedString + " ");
        }

        return decoded.toString();
    }

    @Override
    public void onGetSuggestions(SuggestionsInfo[] results) {
    }

    @Override
    public void onGetSentenceSuggestions(SentenceSuggestionsInfo[] results) {
        for(SentenceSuggestionsInfo result:results){
            int n = result.getSuggestionsCount();
            for(int i=0; i < n; i++){
                int m = result.getSuggestionsInfoAt(i).getSuggestionsCount();

                for(int k=0; k < m; k++) {
                    correctedString = result.getSuggestionsInfoAt(i).getSuggestionAt(k);
                }
            }
        }
    }

    private void fetchSuggestionsFor(String input){
        TextServicesManager tsm =
                (TextServicesManager) getSystemService(TEXT_SERVICES_MANAGER_SERVICE);

        SpellCheckerSession session =
                tsm.newSpellCheckerSession(null, null, VowelsByPointsCipher.this, true);

        session.getSentenceSuggestions(
                new TextInfo[]{ new TextInfo(input) },
                1
        );
    }
}
