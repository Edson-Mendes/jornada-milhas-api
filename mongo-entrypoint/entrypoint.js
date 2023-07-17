db.createUser({ user: "user", pwd: "1234", roles: [ { role: "readWrite", db: "jornada-milhas-db"} ]});

db.createCollection("project_info");

db.project_info.insert({
    name: 'Jornada Milhas API',
    language: "Java 17",
    framework: "Spring",
    started_at: ISODate("2023-07-17T14:00:00.000Z"),
});