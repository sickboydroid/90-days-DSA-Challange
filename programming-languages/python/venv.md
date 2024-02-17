# Venv

- Virtual Environments (VEs) allow us to manage separate package installations for different projects
- You can create `requirements.txt` that declares dependencies
- While moving projects, recreate environment rather than moving it.
- Scripts installed in VE will work as expected even without activating VE. Thus `<path-to-env>/bin/pip uninstall bs4` will remove package bs4 from VE rather than globally even if VE is not activated
  - Your scripts should contain shebang like `#!<path-env/bin/python` in-order to mimic above behavior
- VEs by default don't have access to system site-packages
- Usually **project_root/.venv** is the directory where VE is created. Consider adding this to `.gitignore`

```bash
# create VE
python -m venv .venv
# activate VE
source env/bin/activate
# deactivate VE
deactivate

# install all packages listed in requirements.txt
pip install -r requirements.txt
# export list of all installed packages and their versions to requirements.txt
pip freeze > requirements.txt


# requirements.txt looks like this
requests==2.18.4
google-auth==1.1.9
```
