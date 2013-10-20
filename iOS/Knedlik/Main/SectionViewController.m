//
//  SectionViewController.m
//  Knedlo
//
//  Created by Martin Wenisch on 10/20/13.
//  Copyright (c) 2013 Ragtime. All rights reserved.
//

#import "SectionViewController.h"
#import "GTLKnedlo.h"

@interface SectionViewController ()

@property (nonatomic, strong) NSArray *filteredDataSource;

@end

@implementation SectionViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view.
    
    FormatFilter *formatFiler = [[FormatFilter alloc] init];
    
    [formatFiler setWidth:self.sourceCategory.categoryImage.size.width height:self.sourceCategory.categoryImage.size.height];
    self.sectionHeaderImage.image = [formatFiler imageByFilteringImage:self.sourceCategory.categoryImage];
    
    //filter data source
    self.filteredDataSource = [self.originalDataSource filteredArrayUsingPredicate:[NSPredicate predicateWithBlock:^BOOL(id evaluatedObject, NSDictionary *bindings) {
        GTLKnedloArticle *article = (GTLKnedloArticle *)evaluatedObject;
        
        if ([article.source isEqualToString:self.sourceCategory.categoryName]) {
            return YES;
        } else {
            return NO;
        }
    }]];
    
    self.dataSource = [self.filteredDataSource mutableCopy];
    [self.collectionView reloadData];
    
    //enable swipe back
    self.navigationController.interactivePopGestureRecognizer.delegate = (id<UIGestureRecognizerDelegate>)self;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)backButtonPressed:(id)sender
{
    [self.navigationController popToRootViewControllerAnimated:YES];
}

@end
