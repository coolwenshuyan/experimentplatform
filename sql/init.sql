Drop DATABASE IF EXISTS  experimentplatform;
CREATE Database experimentplatform;
Use experimentplatform;
GRANT All ON experimentplatform.*TO 'experiment'@'localhost'IDENTIFIED by 'experiment@platform20200506';