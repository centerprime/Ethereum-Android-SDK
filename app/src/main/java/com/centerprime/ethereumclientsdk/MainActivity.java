package com.centerprime.ethereumclientsdk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.centerprime.ethereum_client_sdk.EthManager;

import java.math.BigDecimal;
import java.math.BigInteger;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EthManager ethManager = EthManager.getInstance();
        ethManager.init("https://mainnet.infura.io/v3/a396c3461ac048a59f389c7778f06689");
        String password = "xxxx12345";
        ethManager.createWallet(password, this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wallet -> {
                    String walletAddress = wallet.getAddress();
                    String keystore = wallet.getKeystore();

                    ethManager.sendEther(walletAddress, password, new BigInteger("30000000000"), BigInteger.valueOf(21000), BigDecimal.valueOf(1), "0x3dF4F80A1592a125742EF7d69C24CC3F8306AFd8",this)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(t -> {
                                System.out.println(t);
                            }, error -> {
                               error.printStackTrace();
                            });

                }, error -> {
                    error.printStackTrace();
                });

//        ethManager.balanceInEth("0x3dF4F80A1592a125742EF7d69C24CC3F8306AFd8")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(va -> {
//                    va.toBigInteger();
//                });


    }
}