create or replace PACKAGE BODY         PKG_LOGIN IS

    PROCEDURE getLOGIN_VALIDATION (iorc IN OUT NOCOPY TYRC_LOGIN) IS
        v_stored_password VARCHAR2(255);
    BEGIN
        -- Retrieve the stored encrypted password from the USERS table based on the provided username
        SELECT PASSWORD_ENCRYPTED INTO v_stored_password
        FROM GEST_SPA.LOGIN
        WHERE USERNAME = iorc.USERNAME;
    
        -- Check if the provided encrypted password matches the stored encrypted password
        IF iorc.PASSWORD_ENCRYPTED = v_stored_password THEN
            -- Passwords match, set IS_AUTHENTICATED to 1 (true) and return success message
            iorc.IS_AUTHENTICATED := 1;
            iorc.MESSAGE := 'Authentication successful.';
        ELSE
            -- Passwords do not match, set IS_AUTHENTICATED to 0 (false) and return error message
            iorc.IS_AUTHENTICATED := 0;
            iorc.MESSAGE := 'Authentication failed. Invalid username or password.';
        END IF;
   EXCEPTION
        WHEN NO_DATA_FOUND THEN
            -- User not found in the database, set IS_AUTHENTICATED to 0 and return error message
            iorc.IS_AUTHENTICATED := 0;
            iorc.MESSAGE := 'Authentication failed. User not found.';
        WHEN OTHERS THEN
            -- Other exceptions, set IS_AUTHENTICATED to 0 and return error message
            iorc.IS_AUTHENTICATED := 0;
            iorc.MESSAGE := 'Authentication failed due to an unexpected error.';
    END getLOGIN_VALIDATION;

/***********************************************************************************/

END PKG_LOGIN;