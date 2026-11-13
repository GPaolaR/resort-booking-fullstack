import { TestBed } from '@angular/core/testing';

import { ApiAgendaService } from './api-agenda.service';

describe('ApiAgendaService', () => {
  let service: ApiAgendaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApiAgendaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
