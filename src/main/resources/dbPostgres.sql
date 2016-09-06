DROP TABLE IF EXISTS role;
CREATE TABLE role (
  id SERIAL PRIMARY KEY ,
  name varchar(45) DEFAULT NULL
);

--
-- Dumping data for table `role`
--


INSERT INTO role VALUES (1,'ROLE_USER');

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS app_user;
CREATE TABLE app_user (
  id SERIAL PRIMARY KEY,
  username varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL
);

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS user_role;
CREATE TABLE user_role (
  user_id bigint,
  role_id bigint, 
  PRIMARY KEY (user_id,role_id),
  FOREIGN KEY (user_id) REFERENCES app_user (id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE CASCADE ON UPDATE CASCADE
);
