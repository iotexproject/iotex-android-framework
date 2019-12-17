# IoTeX Antenna Java

[![LICENSE](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)

Welcome to the official Java implementation of IoTeX Antenna Java! IoTeX is building the next generation of the decentralized 
network for IoT powered by scalability- and privacy-centric blockchains. Please refer to IoTeX
[whitepaper](https://iotex.io/academics) for details.

## Get started

### Install By Maven

```
<dependency>
  <groupId>com.github.iotexproject</groupId>
  <artifactId>iotex-antenna-java</artifactId>
  <version>0.5.3</version>
</dependency>
```

### Install by Gradle

```
implementation 'com.github.iotexproject:iotex-antenna-java:0.5.3'
```


### Sample

```java
// create account from private key
Account account = IotexAccount.create(IotexAccountTest.TEST_PRIVATE);
IOTX iotx = new IOTX("api.testnet.iotex.one:80");

// create KeyStore file
KeystoreFile walletFile = Keystore.createStandard("123456", Numeric.toBigInt(account.privateKey()));

// transfer
TransferRequest request = new TransferRequest();
request.setNonce(1l); // optional, can be null
request.setGasLimit(100000l); // optional, can be null
request.setGasPrice("1000000000000"); // optional, can be null
request.setAccount(account);
request.setAmount("100");
request.setRecipient("io13zt8sznez2pf0q0hqdz2hyl938wak2fsjgdeml");
request.setPayload("68656c6c6f20776f726c6421"); // optional, can be null

String hash = iotx.sendTransfer(request);

// contract deploy
Contract contract = new Contract(iotx.currentProvider(), CONTRACT_ABI, Numeric.hexStringToByteArray(CONTRACT_BIN));
String hash = contract.deploy(null, 100000l, "1000000000000", account, "0", 5);

// contract execute
Contract contract = new Contract(provider, CONTRACT_ADDRESS, CONTRACT_ABI);
String hash = contract.execute(null, 100000l, "1000000000000", account, "set", "0", 10);
```
