/************************************************************************************
Description: Object responsible for .
Author:      Gina Riveros
Date:        18-10-2023
Project:     Oasis Resort and Spa
************************************************************************************/

CREATE TYPE TYRC_LOGIN AS OBJECT
(
	USERNAME VARCHAR2(50), 
	PASSWORD_ENCRYPTED VARCHAR2(255), 
	IS_AUTHENTICATED NUMBER(1),
    --COD_ERROR NUMBER(5),
    MESSAGE VARCHAR2(255)	
);
/