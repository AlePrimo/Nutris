CREATE USER 'nutris'@'localhost' IDENTIFIED BY 'sistemanutris';
GRANT ALL PRIVILEGES ON nutris.* TO 'nutris'@'localhost';
FLUSH PRIVILEGES;