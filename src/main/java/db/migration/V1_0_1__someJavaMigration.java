package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.flywaydb.core.internal.jdbc.JdbcTemplate;

public class V1_0_1__someJavaMigration extends BaseJavaMigration {

  @Override
  public void migrate(final Context context) throws Exception {
    final JdbcTemplate jdbcTemplate = new JdbcTemplate(context.getConnection());
    jdbcTemplate.executeStatement("SELECT 1");
  }
}
