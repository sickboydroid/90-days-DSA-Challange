# Recipies

## Monitoring network

### METHOD 1

- Get your SSL certificate
- Install on your phone
- Then modified the apk with following code

```xml
<!-- Add this to the res/xml/network_config.xml of app -->
<network-security-config>
    <base-config>
        <trust-anchors>
            <certificates src="user" />
            <certificates src="system" />
        </trust-anchors>
    </base-config>
 </network-security-config>

<!-- Add this line to AndroidManifest.xml -->
<application
        android:networkSecurityConfig="@xml/network_config"
        .../>
```