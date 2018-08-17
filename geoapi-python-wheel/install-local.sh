##########################################################
# Generate pip package and redeploy it on current system
# Author : Johann Sorel (Geomatys)
##########################################################


# Create python package
python3 build.py sdist

# Uninstall any previous version
sudo pip3 uninstall -y ogc

# Install new one
sudo pip3 install dist/ogc-4.0.tar.gz
