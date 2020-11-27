# Ethereum Client SDK 


## Table of Contents

- [Getting Started](#getting-started)
  - [Adding Ethereum SDK as a Maven or Gradle Dependency](#adding-ethereum-sdk-as-a-maven-or-gradle-dependency)
  - [Basic Usage](#basic-usage)
- [Features at a Glance](#features-at-a-glance)
  - [Create Wallet](#create-wallet)
  - [Import Wallet By Keystore](#import-wallet-by-keystore)
  - [Import Wallet By Private Key](#import-wallet-by-private-key)
  - [Export Keystore](#export-keystore)
  - [Export Private Key](#export-private-key)
  - [Ethereum Balance](#ethereum-balance)
  - [ERC20 token balance](#erc20-token-balance)
  - [Send Ether](#send-ether)
  - [Send ERC20 Token](#send-erc20-token)

## Getting Started

### Add jitpack to your root gradle file at the end of repositories:
```xml
allprojects {
    repositories {
	...
        maven { url 'https://jitpack.io'}
    }
}
```
### Adding Ethereum SDK as a Maven or Gradle Dependency
For “Maven” add this dependency to your module:

```xml
<dependency>
	    <groupId>com.github.centerprime</groupId>
	    <artifactId>Ethereum-Client-SDK</artifactId>
	    <version>1.0.8</version>
</dependency>
```

For “Gradle” add this dependency to your module:

```groovy
dependencies {
    implementation 'com.github.centerprime:Ethereum-Client-SDK:1.0.8'
}
```

### Basic Usage

Once you have the dependencies set up you can start using *CenterPrime* by creating a `Ethereum Wallet`:

```java
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.centerprime.ethereum_sdk.EthManager;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EthManager ethManager = EthManager.getInstance();

        String password = "xxxx12345";

        ethManager.createWallet(password, this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wallet -> {
                    String walletAddress = wallet.getAddress();
                    String keystore = wallet.getKeystore();
                }, error -> {

                });
    }
}
```

*Congratulations!* Now you are a *CenterPrime* user.

## Features at a Glance

### Create Wallet

> You can create Ethereum Wallet.
```java
EthManager ethManager = EthManager.getInstance();
String password = "xxxx12345";
ethManager.createWallet(password, this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(wallet -> {
                    String walletAddress = wallet.getAddress();
                    String keystore = wallet.getKeystore();
                }, error -> {

                });
```

### Import Wallet By Keystore

> To import Ethereum wallet by keystore you can use a code written below. 

```java
EthManager ethManager = EthManager.getInstance();
String password = "xxxx12345";
String keystore = "JSON_FORMAT";
ethManager.importFromKeystore(keystore, password, this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(walletAddress -> {

                    Toast.makeText(this, "Wallet Address : " + walletAddress, Toast.LENGTH_SHORT).show();

                }, error -> {

                });
```
### Import Wallet By Private Key

> To import Ethereum wallet by private key you can use a code written below.

```java
EthManager ethManager = EthManager.getInstance();
String privateKey = "PRIVATE_KEY";
ethManager.importFromPrivateKey(privateKey, this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(walletAddress -> {

                    Toast.makeText(this, "Wallet Address : " + walletAddress, Toast.LENGTH_SHORT).show();

                }, error -> {

                });
```

### Export Keystore

> If you want to export wallet address’s keystore you can use a code written below.

```java
EthManager ethManager = EthManager.getInstance();
String walletAddress = "WALLET_ADDRESS";
ethManager.getKeyStore(walletAddress, this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(keystore -> {

                    Toast.makeText(this, "Keystore : " + keystore, Toast.LENGTH_SHORT).show();

                }, error -> {

                });
```

### Export Private Key

> If you want to export wallet address’s private key you can use a code written below.

```java
EthManager ethManager = EthManager.getInstance();
String walletAddress = "WALLET_ADDRESS";
String password = "WALLET_PASSWORD";
ethManager.exportPrivateKey(walletAddress, password,this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(privatekey -> {

                    Toast.makeText(this, "Private Key : " + privatekey, Toast.LENGTH_SHORT).show();

                }, error -> {

                });
```

### Ethereum Balance

> To get balance of Ethereum wallet address you can use this code.

```java
EthManager ethManager = EthManager.getInstance();
String walletAddress = "WALLET_ADDRESS";
ethManager.balanceInEth(walletAddress)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(balance -> {

                    Toast.makeText(this, "Eth Balance : " + balance, Toast.LENGTH_SHORT).show();

                }, error -> {

                });
```
### ERC20 token balance

> To get balance of ERC20 token you can use this code. 

```java
EthManager ethManager = EthManager.getInstance();
String walletAddress = "WALLET_ADDRESS";
String password = "WALLET_PASSWORD";
String erc20TokenContractAddress = "ERC_20_TOKEN_CONTRACT_ADDRESS";
ethManager.getTokenBalance(walletAddress, password, erc20TokenContractAddress, this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(balance -> {

                    Toast.makeText(this, "Token Balance : " + balance, Toast.LENGTH_SHORT).show();

                }, error -> {

                });
```

### Send Ether

> To send Ethereum to another wallet address you can use this code.

```java
EthManager ethManager = EthManager.getInstance();
String walletAddress = "WALLET_ADDRESS";
String password = "WALLET_PASSWORD";
BigInteger gasPrice = new BigInteger("GAS_PRICE");
BigInteger gasLimit = new BigInteger("GAS_LIMIT");
BigDecimal etherAmount = new BigDecimal("ETHER_AMOUNT");
String receiverAddress = "RECEIVER_WALLET_ADDRESS";
ethManager.sendEther(walletAddress, password,gasPrice,gasLimit,etherAmount, receiverAddress, this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tx -> {

                    Toast.makeText(this, "TX : " + tx, Toast.LENGTH_SHORT).show();

                }, error -> {

                });
```
### Send ERC20 token

> To send ERC20 Token to another wallet address you can use this code.

```java
EthManager ethManager = EthManager.getInstance();
String walletAddress = "WALLET_ADDRESS";
String password = "WALLET_PASSWORD";
BigInteger gasPrice = new BigInteger("GAS_PRICE");
BigInteger gasLimit = new BigInteger("GAS_LIMIT");
BigDecimal tokenAmount = new BigDecimal("TOKEN_AMOUNT");
String receiverAddress = "RECEIVER_WALLET_ADDRESS";
String erc20TokenContractAddress = "ERC20_TOKEN_CONTRACT_ADDRESS";
ethManager.sendToken(walletAddress, password, gasPrice, gasLimit, tokenAmount, receiverAddress, erc20TokenContractAddress, this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tx -> {

                    Toast.makeText(this, "TX : " + tx, Toast.LENGTH_SHORT).show();

                }, error -> {

                });
```

