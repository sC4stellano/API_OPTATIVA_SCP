-- Inserción en la tabla status (tabla padre)
INSERT IGNORE INTO
    Proyectosdb.status (status_id, status_name)
VALUES (1, 'En progreso'),
    (2, 'Completado'),
    (3, 'Pendiente'),
    (4, 'Cancelado'),
    (5, 'En espera');

-- Inserción en la tabla technologies
INSERT IGNORE INTO
    Proyectosdb.technologies (tech_name)
VALUES ('Java'),
    ('C#'),
    ('Python'),
    ('JavaScript'),
    ('SQL');

-- Inserción en la tabla developers
INSERT IGNORE INTO
    Proyectosdb.developers (
        devname,
        dev_surname,
        email,
        linkedin_url,
        github_url
    )
VALUES (
        'Laura',
        'García Pérez',
        'laura.garcia@example.com',
        'https://linkedin.com/in/lauragarcia',
        'https://github.com/lauragp'
    ),
    (
        'Carlos',
        'Sánchez López',
        'carlos.sanchez@example.com',
        'https://linkedin.com/in/carlossanchez',
        'https://github.com/csanchez'
    ),
    (
        'Marta',
        'Fernández Ruiz',
        'marta.fernandez@example.com',
        'https://linkedin.com/in/martafernandez',
        'https://github.com/mfernandez'
    ),
    (
        'David',
        'Martínez Gómez',
        'david.martinez@example.com',
        'https://linkedin.com/in/davidmartinez',
        'https://github.com/dmartinez'
    ),
    (
        'Ana',
        'López Díaz',
        'ana.lopez@example.com',
        'https://linkedin.com/in/analopez',
        'https://github.com/alopez'
    );

-- Inserción en la tabla projects (requiere status ya insertado)
INSERT IGNORE INTO
    Proyectosdb.projects (
        project_name,
        description,
        start_date,
        end_date,
        demo_url,
        picture,
        status_status_id
    )
VALUES (
        'Proyecto A',
        'Aplicación móvil para gestión de tareas',
        '2025-01-01',
        '2025-06-30',
        'https://demo.proyectoA.com',
        'proyectoA.jpg',
        1
    ),
    (
        'Proyecto B',
        'Web de comercio electrónico',
        '2024-12-15',
        '2025-05-15',
        'https://demo.proyectoB.com',
        'proyectoB.jpg',
        2
    ),
    (
        'Proyecto C',
        'Plataforma de aprendizaje online',
        '2025-02-01',
        '2025-08-01',
        'https://demo.proyectoC.com',
        'proyectoC.jpg',
        3
    ),
    (
        'Proyecto D',
        'Sistema CRM para ventas',
        '2023-11-20',
        '2024-12-20',
        'https://demo.proyectoD.com',
        'proyectoD.jpg',
        4
    ),
    (
        'Proyecto E',
        'Sitio web corporativo',
        '2024-06-01',
        '2024-11-01',
        'https://demo.proyectoE.com',
        'proyectoE.jpg',
        5
    );

-- Inserción en technologies_used_in_projects (requiere technologies y projects ya insertados)
INSERT IGNORE INTO
    Proyectosdb.technologies_used_in_projects (
        tecnologia_tecnologia_id,
        projects_project_id
    )
VALUES (1, 1),
    (2, 1),
    (3, 2),
    (4, 2),
    (5, 3);

-- Inserción en developers_worked_on_projects (requiere developers y projects ya insertados)
INSERT IGNORE INTO
    Proyectosdb.developers_worked_on_projects (
        developer_dev_id,
        projects_project_id
    )
VALUES (1, 1),
    (2, 1),
    (3, 2),
    (4, 3),
    (5, 4);