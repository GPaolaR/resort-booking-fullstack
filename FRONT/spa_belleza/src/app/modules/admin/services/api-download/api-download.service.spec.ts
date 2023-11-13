import { TestBed } from '@angular/core/testing';

import { ApiDownloadService } from './api-download.service';

describe('ApiDownloadService', () => {
  let service: ApiDownloadService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApiDownloadService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
