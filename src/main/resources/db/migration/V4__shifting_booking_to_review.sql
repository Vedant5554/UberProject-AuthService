
SET @fk_exists = (
    SELECT COUNT(*)
    FROM information_schema.KEY_COLUMN_USAGE
    WHERE TABLE_NAME = 'booking'
      AND CONSTRAINT_NAME = 'FK_BOOKING_ON_REVIEW'
);

SET @sql = IF(@fk_exists > 0,
              'ALTER TABLE booking DROP FOREIGN KEY FK_BOOKING_ON_REVIEW;',
              'SELECT 1;'
           );
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;


-- Add column safely
ALTER TABLE booking_review
    ADD COLUMN booking_id BIGINT;


-- Add FK safely (only if not exists)
SET @fk2_exists = (
    SELECT COUNT(*)
    FROM information_schema.KEY_COLUMN_USAGE
    WHERE TABLE_NAME = 'booking_review'
      AND CONSTRAINT_NAME = 'FK_BOOKINGREVIEW_ON_BOOKING'
);

SET @sql2 = IF(@fk2_exists = 0,
               'ALTER TABLE booking_review ADD CONSTRAINT FK_BOOKINGREVIEW_ON_BOOKING FOREIGN KEY (booking_id) REFERENCES booking (id);',
               'SELECT 1;'
            );
PREPARE stmt2 FROM @sql2;
EXECUTE stmt2;
DEALLOCATE PREPARE stmt2;




