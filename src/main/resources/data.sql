-- Insert 50 tasks with varied data, following the specified TaskPriority and TaskStatus enums
DO
$$
DECLARE
i INT;
    task_priority TEXT;
    task_status TEXT;
    due_date TIMESTAMP;
BEGIN
FOR i IN 1..50 LOOP
        -- Generate random priorities (LOW, MEDIUM, HIGH)
        task_priority := CASE
            WHEN i % 3 = 0 THEN 'HIGH'
            WHEN i % 3 = 1 THEN 'MEDIUM'
            ELSE 'LOW'
END;

        -- Generate random statuses (TODO, IN_PROGRESS, DONE)
        task_status := CASE
            WHEN i % 3 = 0 THEN 'TODO'
            WHEN i % 3 = 1 THEN 'IN_PROGRESS'
            ELSE 'DONE'
END;

        -- Generate varied due dates (past and future)
        due_date := NOW() + (i * INTERVAL '1 day') - (i * INTERVAL '5 hours');

        -- Insert task
INSERT INTO public.tasks (title, description, priority, status, due_date)
VALUES (
           'Task ' || i, -- Title
           'Description for Task ' || i, -- Description
           task_priority, -- Priority (HIGH, MEDIUM, LOW)
           task_status,   -- Status (TODO, IN_PROGRESS, DONE)
           due_date       -- Due date
       );
END LOOP;
END;
$$;


DO
$$
    DECLARE
        i INT;
        user_role TEXT;
        created_at TIMESTAMP;
        updated_at TIMESTAMP;
    BEGIN
        FOR i IN 1..10 LOOP
                -- Generate random roles (ADMIN, USER)
                user_role := CASE
                                 WHEN i % 2 = 0 THEN 'USER'
                                 ELSE 'ADMIN'
                    END;

                -- Generate random timestamps
                created_at := NOW() - (i * INTERVAL '1 day'); -- Creates dates for the last 10 days
                updated_at := created_at + (INTERVAL '1 hour' * (i % 24)); -- Randomly update within the same day

                -- Insert user
                INSERT INTO public.users (username, email, password, role, created_at, updated_at)
                VALUES (
                           'user' || i, -- Username
                           'user' || i || '@example.com', -- Email
                           'password' || (i + 1000), -- Password
                           user_role, -- Role (ADMIN, USER)
                           created_at, -- Created at timestamp
                           updated_at  -- Updated at timestamp
                       );
            END LOOP;
    END;
$$;
