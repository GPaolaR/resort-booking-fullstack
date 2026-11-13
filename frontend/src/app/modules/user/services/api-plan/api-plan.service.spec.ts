import { TestBed } from '@angular/core/testing';

import { ApiPlanService } from './api-plan.service';

describe('ApiPlanService', () => {
  let service: ApiPlanService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApiPlanService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
