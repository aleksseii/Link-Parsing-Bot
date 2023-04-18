import jakarta.validation.constraints.NotNull;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.DirectoryResourceAccessor;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class IntegrationEnvironment {

    private static final @NotNull String POSTGRES_IMAGE_NAME = "postgres:15";
    private static final @NotNull String MASTER_CHANGELOG_NAME = "master.xml";
    private static final @NotNull Path MASTER_PATH = Paths.get("../migrations");

    private static final @NotNull PostgreSQLContainer<?> POSTGRES_CONTAINER;


    static {
        POSTGRES_CONTAINER = new PostgreSQLContainer<>(POSTGRES_IMAGE_NAME);
        POSTGRES_CONTAINER.start();


        try {
            final Connection connection = getConnection();
            JdbcConnection jdbcConnection = new JdbcConnection(connection);
            Database database = DatabaseFactory
                    .getInstance()
                    .findCorrectDatabaseImplementation(jdbcConnection);

            Liquibase liquibase = new Liquibase(
                    MASTER_CHANGELOG_NAME,
                    new DirectoryResourceAccessor(MASTER_PATH),
                    database
            );

            // TODO: find a non-deprecated way to update
            liquibase.update(
                    new Contexts(),
                    new LabelExpression()
            );
        } catch (SQLException | FileNotFoundException | LiquibaseException e) {
            throw new RuntimeException(e);
        }
    }

    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                POSTGRES_CONTAINER.getJdbcUrl(),
                POSTGRES_CONTAINER.getUsername(),
                POSTGRES_CONTAINER.getPassword()
        );
    }
}
