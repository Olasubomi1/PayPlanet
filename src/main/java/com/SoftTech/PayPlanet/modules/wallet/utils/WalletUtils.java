package com.SoftTech.PayPlanet.modules.wallet.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WalletUtils {
    private static final int MAX_WALLET_ID_NUM = 10;

    public static Long generateUniqueWalletId(){
        String timeString = String.valueOf(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        String[] tokenArray = timeString.split("");
        StringBuilder builder = new StringBuilder();
        if(tokenArray.length > 10){
            Collections.reverse(new ArrayList<>(List.of(tokenArray)));
            for(int i = 0; i < 10; i++){
                String token = tokenArray[i];
                builder.append(token);
            }
            return Long.parseLong(builder.toString());
        }
        return Long.parseLong(String.join("", tokenArray));
    }
}
