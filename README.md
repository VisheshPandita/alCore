# Set-up guide
## First time setup
* Set-up docker containers
    ```shell
    docker compose up
    ```
* Set-up db
    ```text
    Go to: localhost:5050 (pgadmin)
    Create a new server (Add a new server, name of server doesn't matter)
          Get connection details for db from docker: 
              docker inspect postgresql -f "{{json .NetworkSettings.Networks }}"
          Copy IPAddress field to connection info on pgadmin
          Username and Password of db can be found in docker-compose.yml file
    Create a new db with name: affiliated_link
          Select owner as user defined in docker-compose.yml file
    ```
* Build & run the codebase
* Important URLs:
    ```text
        localhost:8080/api/v1/users/auth/register
        localhost:8080/api/v1/users/auth/authenticate
        localhost:8080/api/v1/users/auth/logout
        localhost:8080/api/v1/users/
        localhost:8080/api/v1/users/{id}
    ```