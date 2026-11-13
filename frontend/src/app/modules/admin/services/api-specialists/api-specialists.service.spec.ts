import { TestBed } from '@angular/core/testing';

import { ApiSpecialistsService } from './api-specialists.service';

describe('ApiFiltersService', () => {
  let service: ApiSpecialistsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApiSpecialistsService);
  });
  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
