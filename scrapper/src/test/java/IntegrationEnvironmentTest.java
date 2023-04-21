import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.testcontainers.shaded.org.hamcrest.MatcherAssert.assertThat;
import static org.testcontainers.shaded.org.hamcrest.Matchers.containsInAnyOrder;

class IntegrationEnvironmentTest extends IntegrationEnvironment {

    private static final @NotNull String SELECT_ALL_TABLES_NAMES = """
            SELECT table_name
              FROM information_schema.tables
             WHERE table_schema = 'public'
               AND table_catalog = CURRENT_DATABASE()
            """;

    @Test
    void when_ContainerStarted_Expect_AllTablesCreated() throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL_TABLES_NAMES);

        List<String> actualTableNames = getTableNames(resultSet);

        assertThat(actualTableNames, containsInAnyOrder(
                "chat",
                "link",
                "chat_link",
                "databasechangelog",
                "databasechangeloglock"
        ));
    }

    private static @NotNull List<@NotNull String> getTableNames(@NotNull ResultSet resultSet) throws SQLException {
        List<String> tableNames = new ArrayList<>();
        while (resultSet.next()) {
            tableNames.add(resultSet.getString("TABLE_NAME"));
        }
        return tableNames;
    }
}
