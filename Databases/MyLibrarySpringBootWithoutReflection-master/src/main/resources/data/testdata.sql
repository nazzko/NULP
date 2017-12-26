USE mylibrary;

INSERT INTO Customer (CustomerName, Surname, Email) VALUES
  ('Vyacheslav','Koldovskyy','koldovsky@gmail.com'),
  ('Andrii','Pavelchak ','apavelchak@gmail.com'),
  ('Andrian','Soluk','andriansoluk@gmail.com'),
  ('Bohdan','Dubyniak','bohdan.dub@gmail.com'),
  ('Igor','Faryna','farynaihor@gmail.com');

INSERT INTO Shop (IDShop,Shop) VALUES (1, 'AliExpress'),(2, 'Taobao'),(3, 'Gearbest'),(4, 'JD'),(5, 'Zapals');

INSERT INTO product (ProductName,Amount,IDShop,Price) VALUES
  ('Lamp', 2548, 1, 5.99),
  ('Powerbank',556, 2 , 12.5),
  ('Phone', 6484 , 3,175),
  ('Toy',2555, 4,2.3),
  ('Pen',644, 5,4.89),
  ('Spinner',485, 2, 5);


