public class Queries {

    //------Hyppigste fire tal med antal forekomster------
    public static final String TOP4_FREQUENT = """
            SELECT number, COUNT(*) AS frequency
            FROM(
            SELECT n1 AS number FROM results
            UNION ALL
            SELECT n2 FROM results
            UNION ALL
            SELECT n3 FROM results
            UNION ALL
            SELECT n4 FROM results
            UNION ALL
            SELECT n5 FROM results
            UNION ALL
            SELECT n6 FROM results
            ) 
            GROUP BY number
            ORDER BY frequency DESC
            LIMIT 4;
            """;

    public static final String TOTAL_DRAWS = "SELECT COUNT(*) AS total FROM results;";
}
