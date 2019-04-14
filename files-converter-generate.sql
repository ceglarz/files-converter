
CREATE TABLE public."CONTACTS" (
    "Id" character varying(36) NOT NULL,
    "Id_customer" character varying(36) NOT NULL,
    "Type" numeric(1,0) NOT NULL,
    "Contact" character varying NOT NULL
);

CREATE TABLE public."CUSTOMERS" (
    "Id" character varying(36) NOT NULL,
    "Name" character varying NOT NULL,
    "Surname" character varying NOT NULL,
    "Age" numeric(3,0)
);


ALTER TABLE ONLY public."CONTACTS"
    ADD CONSTRAINT "CONTACTS_pkey" PRIMARY KEY ("Id");


ALTER TABLE ONLY public."CUSTOMERS"
    ADD CONSTRAINT "CUSTOMERS_pkey" PRIMARY KEY ("Id");