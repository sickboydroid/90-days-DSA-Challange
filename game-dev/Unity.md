# Unity

## Shortcuts

### Shortcurts

1. `zoom`: mouse wheel
2. `focus`: select object and hit F
3. `switch tools`: Q W E R T
4. `duplicate`: ctrl+D
5. `maximize view`: Hover and hit shift+space
6. 

## How Unity Scripts work?

- Scripts are just custom  components
- Calculate how much of force, speed or distance you wanna travel and multiple with `Time.deltaTime`
- You should use `FixedUpdate` for physics

## Text (TextMeshPro or TMP)

## Coroutines

1. Functions that work in intervals
2. `StartCoroutine(MeraCoroutine(true))`: Syntax for calling a Coroutine

```csharp
IEnumerator MeraCoroutine(bool isAlive) {
    while(isAlive) {
        Log("Player is still alive");
        yield return null; // control will reach here when next update is called
    }
    Log("Player is dead");
    yield return new WaitForSeconds(3f); // control will reach here after 3 seconds
    Log("Game and Coroutine both ended");
}
```