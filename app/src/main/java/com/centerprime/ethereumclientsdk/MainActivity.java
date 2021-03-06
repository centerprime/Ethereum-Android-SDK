package com.centerprime.ethereumclientsdk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.centerprime.ethereum_client_sdk.EthManager;

import java.math.BigDecimal;
import java.math.BigInteger;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    EthManager ethManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ethManager = EthManager.getInstance();
        ethManager.init("https://ropsten.infura.io/v3/a396c3461ac048a59f389c7778f06689");
        String password = "xxxx12345";
        ethManager.createWallet(password, this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wallet -> {
                    String walletAddress = wallet.getAddress();
                    String keystore = wallet.getKeystore();
                    System.out.println(walletAddress+"***");
                    Toast.makeText(this, wallet.getAddress(), Toast.LENGTH_SHORT).show();
                }, error -> {
                    error.printStackTrace();
                });

        ethManager.searchTokenByContractAddress("0x31f42841c2db5173425b5223809cf3a38fede360",
                "0x453d8d2d788a3a8681bd520b409d3705e06f27f8",
                "xxxx12345",
                this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(token -> {
                    System.out.println(token.getContractAddress());
                    System.out.println(token.getDecimals());
                    System.out.println(token.getSymbol());
                    System.out.println(token.getTokenName());
                }, error -> {
                    System.out.println(error.getMessage());
                });

    }

}