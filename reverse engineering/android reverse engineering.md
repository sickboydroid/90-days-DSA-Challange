# Reverse Engineering

## Using smalidea plugin

1. Just decompile the apk using apktool
2. Make sure you have enabled debugging. Add following line to AndroidManifest.xml:

    ```xml
    android:debuggable="true"
    ```

3. Go to android studio and open the decompiled apk as project
4. It will detect framework, just hit update
     that is it

## Sigining and installing android apks

1. Use d2j-apk-sign to sign apks

    ```shell
        d2j-apk-sign [-o out-signed.apk] apk_to_sign.apk 
    ```

2. Install apps vai adb using any one of the following commands.

    ```shell
        # for normal apks
        adb install apk1
        # for split apks
        adb install-multiple apk1 apk2 apk3 ...
    ```

## Using axml to convert xml-bin to xml

- The realname is xml2axml

```bash
# Ascii to bin
axml e [AndroidManifest-readable-in.xml] [AndroidManifest-bin-out.xml]

# Bin to ascii
axml d [AndroidManifest-bin-in.xml] [AndroidManifest-readable-out.xml]
```
