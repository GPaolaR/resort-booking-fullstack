import { TestBed } from '@angular/core/testing';

import { ApiAppointmentsService } from './api-appointments.service';

describe('ApiAppointmentsService', () => {
  let service: ApiAppointmentsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApiAppointmentsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
