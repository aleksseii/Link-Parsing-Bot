import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class IntegrationEnvironmentTest {

    private static final @NotNull String SELECT_ALL_TABLES = """
            SELECT table_name
            FROM information_schema.tables
            WHERE table_schema = 'public' AND table_catalog = CURRENT_DATABASE()
            """;

    @Test
    void when_ContainerStarted_Expect_AllTablesCreated() throws SQLException {
        Connection connection = IntegrationEnvironment.getConnection();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(SELECT_ALL_TABLES);

        Assertions.assertTrue(
                getTableNames(result)
                        .containsAll(List.of(
                                "chat",
                                "link",
                                "chat_link",
                                "databasechangelog",
                                "databasechangeloglock"
                        ))
        );
    }

    private List<String> getTableNames(ResultSet result) throws SQLException {
        List<String> tableNames = new ArrayList<>();
        while (result.next()) {
            tableNames.add(result.getString("TABLE_NAME"));
        }
        return tableNames;
    }
}
