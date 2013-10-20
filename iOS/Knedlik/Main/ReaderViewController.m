//
//  ReaderViewController.m
//  Knedlik
//
//  Created by Martin Wenisch on 10/19/13.
//  Copyright (c) 2013 Ragtime. All rights reserved.
//

#import "ReaderViewController.h"
#import "ReaderCell.h"
#import <QuartzCore/QuartzCore.h>
#import "CustomFlowLayout.h"
#import "AppDelegate.h"
#import "ImageLoader.h"
#import "DetailViewController.h"


@interface ReaderViewController ()

@property (nonatomic, strong) LeftMenuViewController *leftMenu;

@property (nonatomic, strong) UIRefreshControl *refreshControl;
@end

@implementation ReaderViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
        
    }
    return self;
}

- (NSMutableArray *)mockData
{
    NSMutableArray *array = [[NSMutableArray alloc] init];
    GTLKnedloArticle *article;
    NSData *data = [NSData dataWithContentsOfFile:[[NSBundle mainBundle] pathForResource:@"feed" ofType:@"json"]];
    
    NSDictionary *response = [NSJSONSerialization JSONObjectWithData:data options:0 error:nil];
    
    for (NSDictionary *item in [response valueForKey:@"items"]) {
        article = [[GTLKnedloArticle alloc] init];
        article.title = [item valueForKey:@"title"];
        article.link = [item valueForKey:@"link"];
        article.image = [item valueForKey:@"image"];
        article.text = [item valueForKey:@"text"];
        article.source = [item valueForKey:@"source"];
        article.descriptionProperty = [item valueForKey:@"description"];
        [array addObject:article];
    }
    
    return array;
    
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view.
    //create data source
//    GTLQueryKnedlo *query = [GTLQueryKnedlo queryForFeed];
//    GTLServiceKnedlo *service = ((AppDelegate *)[[UIApplication sharedApplication] delegate]).knedloService;
//    [service executeQuery:query completionHandler:^(GTLServiceTicket *ticket, GTLKnedloArticleCollection *object, NSError *error) {
//        self.dataSource = [[object items] mutableCopy];
//        self.leftMenu.dataSource = self.dataSource;
//        [self.collectionView reloadData];
//        // Do something with items.
//    }];
    self.dataSource = [self mockData];
    [self.collectionView reloadData];
    
    
    //custom layout
    CustomFlowLayout *customLayout = [[CustomFlowLayout alloc] init];
    self.collectionView.collectionViewLayout = customLayout;
    
    UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"Knedlik_iPad" bundle:nil];
    self.leftMenu = [storyboard instantiateViewControllerWithIdentifier:@"LeftMenu"];
    self.leftMenu.noiseView = self.noiseView;
    [self.view addSubview:self.leftMenu.view];
    [self addChildViewController:self.leftMenu];
    self.leftMenu.view.translatesAutoresizingMaskIntoConstraints = NO;
    
    //left side distance (for moving)
    self.leftMenu.sideConstraint =[NSLayoutConstraint
                                       constraintWithItem:self.leftMenu.view
                                       attribute:NSLayoutAttributeLeading
                                       relatedBy:NSLayoutRelationEqual
                                       toItem:self.view
                                       attribute:NSLayoutAttributeLeading
                                       multiplier:1.0
                                       constant:-325];
    [self.view addConstraint:self.leftMenu.sideConstraint];
    
    //pinned to top of superview
    NSLayoutConstraint * myConstraint = [NSLayoutConstraint
                   constraintWithItem:self.leftMenu.view
                   attribute:NSLayoutAttributeTop
                   relatedBy:NSLayoutRelationEqual
                   toItem:self.view
                   attribute:NSLayoutAttributeTop
                   multiplier:1.0
                   constant:0];
    myConstraint.priority = 1000;
    [self.view addConstraint:myConstraint];
    
    //pinned to bottom of superview
    myConstraint =[NSLayoutConstraint
                   constraintWithItem:self.leftMenu.view
                   attribute:NSLayoutAttributeBottom
                   relatedBy:NSLayoutRelationEqual
                   toItem:self.bottomLayoutGuide
                   attribute:NSLayoutAttributeTop
                   multiplier:1.0
                   constant:0];
    myConstraint.priority = 1000;
    [self.view addConstraint:myConstraint];
    
    //fixed width
    myConstraint =[NSLayoutConstraint
                   constraintWithItem:self.leftMenu.view
                   attribute:NSLayoutAttributeWidth
                   relatedBy:NSLayoutRelationEqual
                   toItem:nil
                   attribute:NSLayoutAttributeNotAnAttribute
                   multiplier:1.0
                   constant:410];
    myConstraint.priority = 1000;
    [self.view addConstraint:myConstraint];
    
    [self.view layoutIfNeeded];
    
    //noise tap
    self.noiseView.userInteractionEnabled = YES;
    UITapGestureRecognizer *tapGesture = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(noiseViewTaped:)];
    [self.noiseView addGestureRecognizer:tapGesture];
    
    //pull to refresh
    self.refreshControl = [[UIRefreshControl alloc] init];
    [self.refreshControl addTarget:self action:@selector(startRefresh:)
                  forControlEvents:UIControlEventValueChanged];
    [self.collectionView addSubview:self.refreshControl];
}

- (void)startRefresh:(UIRefreshControl *)sender
{
    NSLog(@"refresh");
//    GTLQueryKnedlo *query = [GTLQueryKnedlo queryForFeed];
//    GTLServiceKnedlo *service = ((AppDelegate *)[[UIApplication sharedApplication] delegate]).knedloService;
//    [service executeQuery:query completionHandler:^(GTLServiceTicket *ticket, GTLKnedloArticleCollection *object, NSError *error) {
//        self.dataSource = [[object items] mutableCopy];
//        self.leftMenu.dataSource = self.dataSource;
//        [self.collectionView reloadData];
//        [self.refreshControl endRefreshing];
//        // Do something with items.
//    }];
    
    self.dataSource = [self mockData];
    [self.collectionView reloadData];
    [self.refreshControl endRefreshing];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
 * UICollectionView data source
 */
- (NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section
{
    if (section == 0) {
        return self.dataSource.count;
    }
    
    return 0;
}

// The cell that is returned must be retrieved from a call to -dequeueReusableCellWithReuseIdentifier:forIndexPath:
- (UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath
{
    ReaderCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"ReaderCell" forIndexPath:indexPath];

    cell.contentView.layer.cornerRadius = 10.0;
    cell.contentView.layer.shadowOffset = CGSizeMake(0.0, 1.0);
    cell.contentView.layer.shadowColor = [[UIColor blackColor] CGColor];
    cell.contentView.layer.shadowRadius = 1.0;
    cell.contentView.layer.shadowOpacity = 0.4;
    
    cell.trashViewLeadingSpace.constant = -100;
    cell.contentViewLeadingSpace.constant = 30;
    cell.contentViewTrailingSpace.constant = 30;
    cell.collectionView = collectionView;
    cell.dataSource = self.dataSource;
    
    UIPanGestureRecognizer *panGesture = [[UIPanGestureRecognizer alloc] initWithTarget:cell action:@selector(panGestureMoved:)];
    panGesture.maximumNumberOfTouches = 1;
    panGesture.minimumNumberOfTouches = 1;
    panGesture.delegate = cell;
    [cell addGestureRecognizer:panGesture];

    
    
    
    GTLKnedloArticle *item = (GTLKnedloArticle *)[self.dataSource objectAtIndex:indexPath.row];
    
    //big image:
    cell.bigImageView.image = nil;
    if (item.image != nil) {
        [ImageLoader loadImageWithURL:[NSURL URLWithString:item.image] completionHandler:^(UIImage *img) {
            dispatch_async(dispatch_get_main_queue(), ^{
                ReaderCell *cellToUpdate = (ReaderCell *)[self.collectionView cellForItemAtIndexPath:indexPath];
                if (cellToUpdate != nil) {
                    FormatFilter *formatFiler = [[FormatFilter alloc] init];
                    [formatFiler setWidth:img.size.width height:img.size.height];
                    cell.bigImageView.image = [formatFiler imageByFilteringImage:img];
                }
            });
        }];
    }
    
    cell.titleLabel.text = item.title;
    cell.descriptionLabel.text =item.descriptionProperty;
    cell.smallIconView.image = nil;
    
    return cell;
}

- (void)didRotateFromInterfaceOrientation:(UIInterfaceOrientation)fromInterfaceOrientation
{
    [self.collectionView performBatchUpdates:nil completion:nil];
    [self.view setNeedsLayout];
    [self.leftMenu didRotateFromInterfaceOrientation:fromInterfaceOrientation];
    
}

- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout  *)collectionViewLayout sizeForItemAtIndexPath:(NSIndexPath *)indexPath
{
    // Adjust cell size for orientation
    if (UIDeviceOrientationIsLandscape([[UIApplication sharedApplication] statusBarOrientation])) {
        return CGSizeMake(1023, 175);
    }
    return CGSizeMake(767, 175);
}

- (void)noiseViewTaped:(UITapGestureRecognizer *)recognizer
{
    [self.leftMenu hideAnimated:YES];
}

/*
 * segue
 */
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    if ([segue.identifier isEqualToString:@"DetailSegue"]) {
        DetailViewController *detailController = (DetailViewController *)[segue destinationViewController];
        GTLKnedloArticle *article = (GTLKnedloArticle *)[self.dataSource objectAtIndex:[[[self.collectionView indexPathsForSelectedItems] objectAtIndex:0] row]];
        
        detailController.article = article;

        //send read action to server
        [self readedAction:article];
    }
    
}

- (void)readedAction:(GTLKnedloArticle *)article
{
//    GTLQueryKnedlo *query = [GTLQueryKnedlo queryForActionWithAction:@"read" articleLink:article.link];
//    GTLServiceKnedlo *service = ((AppDelegate *)[[UIApplication sharedApplication] delegate]).knedloService;
//    [service executeQuery:query completionHandler:^(GTLServiceTicket *ticket, GTLKnedloBadgeCollection *object, NSError *error) {
//        NSLog(@"response: %@", object);
//        
//        // Do something with items.
//    }];
}

@end
