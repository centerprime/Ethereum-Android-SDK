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
        ethManager.init("https://mainnet.infura.io/v3/a396c3461ac048a59f389c7778f06689");
        String password = "xxxx12345";
        ethManager.createWallet(password, this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wallet -> {
                    String walletAddress = wallet.getAddress();
                    String keystore = wallet.getKeystore();
                    Toast.makeText(this, wallet.getAddress(), Toast.LENGTH_SHORT).show();
                    System.out.println("****************"+wallet.getAddress());
                    a(walletAddress);
                }, error -> {
                    error.printStackTrace();
                });

        a("0x115ec0771d5715fe2c0b61ce0c2cb2a20e4c683d");



    }
    public void a(String walletAddress){
        ethManager.getTokenBalance(walletAddress, "xxxx12345", "0x913903bD683914288FDaa812cC2f51F243cCC731", this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tx -> {

                    Toast.makeText(this, "TX token balance : " + tx, Toast.LENGTH_SHORT).show();

                }, error -> {
                    Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    System.out.println(error.getMessage());
                });



/*        String password1 = "xxxx12345";
        BigInteger gasPrice = new BigInteger("30000000000");
        BigInteger gasLimit = new BigInteger("100000");
        BigDecimal tokenAmount = new BigDecimal("100");
        String receiverAddress = "0x37eb5b96dbe7eb691d450e84c632c21cf2ac858d";
        String erc20TokenContractAddress = "0x913903bD683914288FDaa812cC2f51F243cCC731";
        ethManager.sendToken(walletAddress, password1, gasPrice, gasLimit, tokenAmount, receiverAddress, erc20TokenContractAddress, this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tx -> {

                    Toast.makeText(this, "TX : " + tx, Toast.LENGTH_SHORT).show();

                }, error -> {
                    Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    System.out.println(error.getMessage());
                });*/

    }
}