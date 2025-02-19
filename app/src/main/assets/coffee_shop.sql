PRAGMA foreign_keys = ON;

CREATE TABLE "clients" (
    "id"			                INTEGER PRIMARY KEY,
    "first_name"	                TEXT NOT NULL,
    "last_name"		                TEXT NOT NULL,
    "email" 		                TEXT NOT NULL UNIQUE CHECK (email LIKE '%@%' AND email LIKE '%.%'),
    "hash"			                TEXT NOT NULL,
    "salt"			                TEXT NOT NULL,
    "phone"			                TEXT NOT NULL
);
CREATE TABLE "shift" (
    "id"         		            INTEGER PRIMARY KEY,
    "start_time" 		            INTEGER NOT NULL,
    "end_time"  		            INTEGER NOT NULL
);

CREATE TABLE "work_schedule" (
    "id"              		        INTEGER PRIMARY KEY,
    "shift_id"       		        INTEGER NOT NULL,
    "work_schedule"   		        TEXT NOT NULL,
    "payment_per_hour" 		        REAL NOT NULL,
    FOREIGN KEY ("shift_id") 	    REFERENCES "shift" ("id") ON DELETE CASCADE
);


CREATE TABLE "positions" (
    "id"        		            INTEGER PRIMARY KEY,
    "position"  		            TEXT NOT NULL UNIQUE
);

CREATE TABLE "employees" (
    "id"        	    	        INTEGER PRIMARY KEY,
    "first_name"	    	        TEXT NOT NULL,
    "last_name"    		            TEXT NOT NULL,
    "email" 			            TEXT NOT NULL UNIQUE CHECK (email LIKE '%@%' AND email LIKE '%.%'),
    "hash"      	     	        TEXT NOT NULL,
    "salt"       	    	        TEXT NOT NULL,
    "position_id"    		        INTEGER,
    "phone"          		        TEXT NOT NULL,
    "birth_date"     		        INTEGER NOT NULL,
    "hire_date"      		        INTEGER NOT NULL,
    "work_schedule_id"  		    INTEGER,
    "status"         		        INTEGER NOT NULL CHECK(status IN (0,1)),
    FOREIGN KEY("position_id") 	    REFERENCES "positions"("id") ON DELETE SET NULL,
    FOREIGN KEY("work_schedule_id")	REFERENCES "shift"("id") ON DELETE SET NULL
);

CREATE TABLE "suppliers" (
    "id"      			            INTEGER PRIMARY KEY,
    "name"    			            TEXT NOT NULL,
    "email" 			            TEXT NOT NULL UNIQUE CHECK (email LIKE '%@%' AND email LIKE '%.%'),
    "phone"   			            TEXT NOT NULL CHECK (LENGTH(phone) >= 10)
);

CREATE TABLE "purchases" (
    "id"            		        INTEGER PRIMARY KEY,
    "supplier_id"   		        INTEGER NOT NULL,
    "purchase_date" 		        INTEGER NOT NULL,
    FOREIGN KEY("supplier_id") 	    REFERENCES "suppliers"("id") ON DELETE CASCADE
);

CREATE TABLE "categories" (
    "id" 			                INTEGER PRIMARY KEY,
    "name" 			                TEXT NOT NULL UNIQUE,
    "category_type"		            TEXT NOT NULL CHECK(category_type IN ('product', 'goods'))
);

CREATE TABLE "units" (
    "id"   			                INTEGER PRIMARY KEY,
    "name" 			                TEXT NOT NULL UNIQUE
);

CREATE TABLE "products" (
    "id"			                INTEGER PRIMARY KEY,
    "name"			                TEXT NOT NULL,
    "category_id"		            INTEGER,
    "quantity"			            DECIMAL(10,2) NOT NULL CHECK (quantity >= 0),
    "supplier_id"		            INTEGER NOT NULL,
    "price"			                DECIMAL(10,2) NOT NULL CHECK (price >= 0),
    "unit_id"			            INTEGER,
    "description"		            TEXT DEFAULT '',
    FOREIGN KEY("category_id") 	    REFERENCES "categories"("id") ON DELETE SET NULL,
    FOREIGN KEY("supplier_id") 	    REFERENCES "suppliers"("id") ON DELETE CASCADE,
    FOREIGN KEY("unit_id") 	        REFERENCES "units"("id") ON DELETE SET NULL
);

CREATE TABLE "purchase_items" (
    "id"            		        INTEGER PRIMARY KEY,
    "purchase_id"   		        INTEGER NOT NULL,
    "product_id"   		            INTEGER NOT NULL,
    "quantity"      		        DECIMAL(10,2) NOT NULL CHECK (quantity > 0),
    "unit_price"    		        DECIMAL(10,2) NOT NULL CHECK (unit_price >= 0),
    FOREIGN KEY("purchase_id") 	    REFERENCES "purchases"("id") ON DELETE CASCADE,
    FOREIGN KEY("product_id") 	    REFERENCES "products"("id") ON DELETE CASCADE
);

CREATE TABLE "goods" (
    "id"			                INTEGER PRIMARY KEY,
    "name"			                TEXT NOT NULL,
    "category_id"		            INTEGER,
    "price"			                DECIMAL(10, 2) NOT NULL CHECK (price >= 0),
    "unit_id"			            INTEGER,
    "quantity"			            DECIMAL(10, 2) NOT NULL DEFAULT 0 CHECK (quantity >= 0),
    "description"		            TEXT DEFAULT '',
    FOREIGN KEY("category_id") 	    REFERENCES "categories"("id") ON DELETE SET NULL,
    FOREIGN KEY("unit_id") 	        REFERENCES "units"("id") ON DELETE SET NULL
);

CREATE TABLE "orders" (
    "id"         		            INTEGER PRIMARY KEY,
    "client_id"  		            INTEGER NOT NULL,
    "order_date" 		            INTEGER NOT NULL,
    FOREIGN KEY("client_id") 	    REFERENCES "Clients"("id") ON DELETE CASCADE
);

CREATE TABLE "order_items" (
    "id"         		            INTEGER PRIMARY KEY,
    "goods_id"    		            INTEGER NOT NULL,
    "quantity"   		            INTEGER NOT NULL CHECK (quantity > 0),
    "unit_price"      		        DECIMAL(10, 2) NOT NULL CHECK (price >= 0),
    "order_id"   		            INTEGER NOT NULL,
    FOREIGN KEY("order_id")         REFERENCES "orders"("id") ON DELETE CASCADE,
    FOREIGN KEY("goods_id")         REFERENCES "goods"("id") ON DELETE CASCADE
);