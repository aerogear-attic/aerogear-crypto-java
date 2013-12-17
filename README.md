[![Build Status](https://api.travis-ci.org/abstractj/cryptoparty.png)](https://api.travis-ci.org/abstractj/cryptoparty)

# AeroGear Crypto Java

A Java API to provide an easy way to use cryptography interfaces for developers built on top of [javax.crypto](http://docs.oracle.com/javase/7/docs/api/javax/crypto/package-summary.html) and [Bouncy Castle](http://www.bouncycastle.org) to support: [AES-GCM authenticated encryption](http://csrc.nist.gov/publications/nistpubs/800-38D/SP-800-38D.pdf), [password based key derivation](http://csrc.nist.gov/publications/nistpubs/800-132/nist-sp800-132.pdf) and [elliptic curve cryptography](http://www.nsa.gov/business/programs/elliptic_curve.shtml).

## Requirements

* JDK 6 or [higher](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Apache Maven](http://maven.apache.org/guides/getting-started/) 

## Installation

### Android

The Android platform unfortunately ships an incomplete and outdated version of [Bouncy Castle for Android](https://code.google.com/p/android/issues/detail?id=3280) which also makes hard to install an updated version of the library. That said, we had to stick with [Spongy Castle](http://rtyley.github.io/spongycastle/), a version of [Bouncy Castle](http://www.bouncycastle.org) repackaged to make it work on Android.

    <dependency>
        <groupId>org.jboss.aerogear</groupId>
        <artifactId>aerogear-crypto</artifactId>
        <version>0.1.1-SNAPSHOT</version>
    </dependency>

    <dependency>
        <groupId>com.madgag</groupId>
        <artifactId>scprov-jdk15on</artifactId>
        <version>1.47.0.3</version>
    </dependency>

### Regular Java projects

For regular Java EE and Java SE projects, [Bouncy Castle](http://www.bouncycastle.org) will be supported and there is no need to workaround it.

    <dependency>
        <groupId>org.jboss.aerogear</groupId>
        <artifactId>aerogear-crypto</artifactId>
        <version>0.1.1-SNAPSHOT</version>
    </dependency>
    
    <dependency>
        <groupId>bouncycastle</groupId>
        <artifactId>bcprov-jdk16</artifactId>
        <version>140</version>
    </dependency>

## Getting started

AeroGear Crypto does not reinvent the wheel by writing encryption algorithms or creating protocols, we still have some sanity. The major goal of this project is to provide simple API interfaces for uber complicated parameters, so let's get started.

### Password based key derivation

    Pbkdf2 pbkdf2 = AeroGearCrypto.pbkdf2();
    byte[] rawKey = pbkdf2.encrypt("passphrase");

### Symmetric encryption

    //Generate the key
    Pbkdf2 pbkdf2 = AeroGearCrypto.pbkdf2();
    byte[] privateKey = pbkdf2.encrypt("passphrase");
    
    //Initializes the crypto box
    CryptoBox cryptoBox = new CryptoBox(privateKey);
    
    //Encryption
    byte[] IV = new Random().randomBytes();
    byte[] ciphertext = cryptoBox.encrypt(IV, "My bonnie lies over the ocean");

    //Decryption
    CryptoBox pandora = new CryptoBox(privateKey);
    byte[] message = pandora.decrypt(IV, ciphertext);

### Asymmetric encryption

    //Create a new key pair
    KeyPair keyPairBob = new KeyPair();
    KeyPair keyPairAlice = new KeyPair();

    //Initializes the crypto box
    CryptoBox cryptoBox = new CryptoBox(keyPairBob.getPrivateKey(), keyPairAlice.getPublicKey());
    
    byte[] IV = new Random().randomBytes();
    byte[] ciphertext = cryptoBox.encrypt(IV, "My bonnie lies over the ocean");

    //Is possible to use the same crypto box instance, but won't happen in real life
    CryptoBox pandora = new CryptoBox(keyPairAlice.getPrivateKey(), keyPairBob.getPublicKey());
    byte[] message = pandora.decrypt(IV, ciphertext);
    
    
We are big believers that there is too much to improve, for this reason you are more than welcome to file a [JIRA](https://issues.jboss.org/browse/AGSEC) if you find any issue or discuss the improvements on the [mailing list](http://aerogear-dev.1069024.n5.nabble.com). Security is not an island and it is our responsibility like developers to make it better.
