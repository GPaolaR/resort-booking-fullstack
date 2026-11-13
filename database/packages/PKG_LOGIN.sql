create or replace PACKAGE PKG_LOGIN IS
/************************************************************************************
Description: Object responsible for managing dml transactions on the LOGIN entity.
Author:      Gina Riveros
Date:        18-10-2023
Project:     Oasis Resort and Spa
------------------------------------------------------------------------------------
Historial de modificaciones=>
Fecha         Orden          Persona                 Acci?n        
------------------------------------------------------------------------------------
18-10-2023    Ticketxxx        Gina Riveros       Creation
************************************************************************************/

    /* Declaracion de tipo de datos publicos */

   -- subtype tyrcLOGIN is  TYRC_LOGIN;


    /************************************************************************************
    Description: Object used to validate the login.
    Author:      Gina Riveros
    Date:        18-10-2023
    Project:     Oasis Resort and Spa
    ------------------------------------------------------------------------------------
    Historial de modificaciones=>
    Fecha         Orden          Persona                 Acci?n        
    ------------------------------------------------------------------------------------
    18-10-2023    Ticketxxx        Gina Riveros       Creation    
    ************************************************************************************/

    procedure getLOGIN_VALIDATION (iorc in out nocopy TYRC_LOGIN);


END PKG_LOGIN;