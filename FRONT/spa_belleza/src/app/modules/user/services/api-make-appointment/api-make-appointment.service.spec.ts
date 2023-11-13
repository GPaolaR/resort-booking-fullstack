import { TestBed } from '@angular/core/testing';

import { ApiMakeAppointmentService } from './api-make-appointment.service';

describe('ApiMakeAppointmentService', () => {
  let service: ApiMakeAppointmentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApiMakeAppointmentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
