import { TestBed } from '@angular/core/testing';

import { ApiPlansService } from './api-plans.service';

describe('ApiPlansService', () => {
  let service: ApiPlansService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApiPlansService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
