ALTER TABLE reservationresto.service DROP COLUMN date_service;
ALTER TABLE reservationresto.reservation ADD date_reservation DATE NOT NULL;
