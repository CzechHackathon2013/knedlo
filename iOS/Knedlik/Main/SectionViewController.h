//
//  SectionViewController.h
//  Knedlo
//
//  Created by Martin Wenisch on 10/20/13.
//  Copyright (c) 2013 Ragtime. All rights reserved.
//

#import "ReaderViewController.h"
#import "SourceCategory.h"

@interface SectionViewController : ReaderViewController

@property (nonatomic, strong) IBOutlet UIImageView *sectionHeaderImage;

@property (nonatomic, strong) SourceCategory *sourceCategory;
@property (nonatomic, strong) NSMutableArray *originalDataSource;

- (IBAction)backButtonPressed:(id)sender;


@end
