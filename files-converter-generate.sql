--
-- PostgreSQL database dump
--

-- Dumped from database version 10.5
-- Dumped by pg_dump version 10.4

-- Started on 2019-04-13 12:47:19

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE "files-converter";
--
-- TOC entry 2158 (class 1262 OID 18743)
-- Name: files-converter; Type: DATABASE; Schema: -; Owner: admin
--

CREATE DATABASE "files-converter" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Polish_Poland.1250' LC_CTYPE = 'Polish_Poland.1250';


ALTER DATABASE "files-converter" OWNER TO admin;

\connect -reuse-previous=on "dbname='files-converter'"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12278)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2161 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 18744)
-- Name: CONTACTS; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."CONTACTS" (
    "Id" character varying(36) NOT NULL,
    "Id_customer" character varying(36) NOT NULL,
    "Type" numeric(1,0) NOT NULL,
    "Contact" character varying NOT NULL
);


ALTER TABLE public."CONTACTS" OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 18752)
-- Name: CUSTOMERS; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."CUSTOMERS" (
    "Id" character varying(36) NOT NULL,
    "Name" character varying NOT NULL,
    "Surname" character varying NOT NULL,
    "Age" numeric(3,0)
);


ALTER TABLE public."CUSTOMERS" OWNER TO postgres;

--
-- TOC entry 2029 (class 2606 OID 18751)
-- Name: CONTACTS CONTACTS_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."CONTACTS"
    ADD CONSTRAINT "CONTACTS_pkey" PRIMARY KEY ("Id");


--
-- TOC entry 2031 (class 2606 OID 18759)
-- Name: CUSTOMERS CUSTOMERS_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."CUSTOMERS"
    ADD CONSTRAINT "CUSTOMERS_pkey" PRIMARY KEY ("Id");


--
-- TOC entry 2160 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2019-04-13 12:47:20

--
-- PostgreSQL database dump complete
--

