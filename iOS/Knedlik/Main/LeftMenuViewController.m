//
//  LeftMenuViewController.m
//  Knedlo
//
//  Created by Martin Wenisch on 10/19/13.
//  Copyright (c) 2013 Ragtime. All rights reserved.
//

#import "LeftMenuViewController.h"
#import "BadgeCell.h"
#import "MenuCell.h"
#import "SectionViewController.h"

@interface LeftMenuViewController ()

@property (nonatomic) BOOL menuVisible;
@property (nonatomic) CGPoint previousLocation;

@property (nonatomic, strong) NSMutableArray *categoryDataSource;
@property (nonatomic, strong) NSIndexPath *selectedIndexPath;

@end

@implementation LeftMenuViewController

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
    
    UIPanGestureRecognizer *panGesture = [[UIPanGestureRecognizer alloc] initWithTarget:self action:@selector(panGestureMoved:)];
    panGesture.maximumNumberOfTouches = 1;
    panGesture.minimumNumberOfTouches = 1;
    [self.menuPull addGestureRecognizer:panGesture];
    self.menuPull.userInteractionEnabled = YES;
    
    //fill categories
    self.categoryDataSource = [[NSMutableArray alloc] init];
    [self.categoryDataSource addObject:[[SourceCategory alloc] initWithSource:@"super.cz" image:[UIImage imageNamed:@"supercz.png"]]];
    [self.categoryDataSource addObject:[[SourceCategory alloc] initWithSource:@"blog.respekt.cz" image:[UIImage imageNamed:@"blogrespekt.png"]]];
    [self.categoryDataSource addObject:[[SourceCategory alloc] initWithSource:@"moviezone.cz" image:[UIImage imageNamed:@"moviezone.png"]]];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)panGestureMoved:(UIPanGestureRecognizer *)recognizer
{
    
    if (recognizer.state == UIGestureRecognizerStateBegan) {
        self.previousLocation = [recognizer locationInView:self.view.superview];
        NSLog(@"started location: %f", [recognizer locationInView:self.view.superview].x);
        self.noiseView.hidden = NO;
    } else if (recognizer.state == UIGestureRecognizerStateEnded || recognizer.state == UIGestureRecognizerStateCancelled) {
        
        if (!self.menuVisible && self.sideConstraint.constant > -250) {
            [self showAnimated:YES];
        } else if (!self.menuVisible && self.sideConstraint.constant <= -250) {
            [self hideAnimated:YES];
        } else if (self.menuVisible && self.sideConstraint.constant > -100) {
            [self showAnimated:YES];
        } else if (self.menuVisible && self.sideConstraint.constant <= -100) {
            [self hideAnimated:YES];
        }
        
        NSLog(@"ended location: %f", [recognizer locationInView:self.view.superview].x);
    } else if (recognizer.state == UIGestureRecognizerStateChanged) {
        NSLog(@"moved location: %f", [recognizer locationInView:self.view.superview].x);
        
        CGFloat diff = [recognizer locationInView:self.view.superview].x - self.previousLocation.x;
        if (self.sideConstraint.constant + diff < -325) {
            self.sideConstraint.constant = -325;
        } else if (self.sideConstraint.constant + diff > 0) {
            self.sideConstraint.constant = 0;
        } else {
            self.sideConstraint.constant = self.sideConstraint.constant + diff;
        }
        
        //update noise view alpha
        self.noiseView.alpha = 1.0 - (self.sideConstraint.constant / -325);
        //self.fadingView.alpha = 0.5 * ((self.mainView.frame.origin.x + diff) / 85);
        
        self.previousLocation = [recognizer locationInView:self.view.superview];
    }
    [self.view layoutIfNeeded];
}

- (void)showAnimated:(BOOL)animated
{
    self.sideConstraint.constant = 0;
    [UIView animateWithDuration:0.2
                     animations:^{
//                         self.view.frame = CGRectMake(0, 0, self.view.frame.size.width, self.view.frame.size.height);
                         [self.view.superview layoutIfNeeded];
                         self.noiseView.alpha = 1.0;
                     } completion:^(BOOL finished) {
                         self.menuVisible = YES;
                     }];
}

- (void)hideAnimated:(BOOL)animated
{
    self.sideConstraint.constant = -325;
    [UIView animateWithDuration:0.2
                     animations:^{
//                         self.view.frame = CGRectMake(-325, 0, self.view.frame.size.width, self.view.frame.size.height);
                         [self.view.superview layoutIfNeeded];
                         self.noiseView.alpha = 0.0;
                     } completion:^(BOOL finished) {
                         self.menuVisible = NO;
                         self.noiseView.hidden = YES;
                     }];
}

- (void)didRotateFromInterfaceOrientation:(UIInterfaceOrientation)fromInterfaceOrientation
{
//    if (UIDeviceOrientationIsLandscape([[UIApplication sharedApplication] statusBarOrientation])) {
//        self.pullBottom.constant = 365;
//    } else {
//        self.pullBottom.constant = 91;
//    }
//    [self.view layoutIfNeeded];
}

- (NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section
{
    if (section == 0) {
        return 6;
    }
    return 0;
}

// The cell that is returned must be retrieved from a call to -dequeueReusableCellWithReuseIdentifier:forIndexPath:
- (UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath
{
    BadgeCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"BadgeCell" forIndexPath:indexPath];
    
    return cell;
}

- (void)collectionView:(UICollectionView *)collectionView didSelectItemAtIndexPath:(NSIndexPath *)indexPath
{
    //item selected go to badge detail
}

/*
 * UItableViewDataSource
 */
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    if (section == 0) {
        return self.categoryDataSource.count;
    }
    return 0;
}

// Row display. Implementers should *always* try to reuse cells by setting each cell's reuseIdentifier and querying for available reusable cells with dequeueReusableCellWithIdentifier:
// Cell gets various attributes set automatically based on table (separators) and data source (accessory views, editing controls)

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    MenuCell *cell = [tableView dequeueReusableCellWithIdentifier:@"MenuCell" forIndexPath:indexPath];
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    cell.categoryImage.image = [[self.categoryDataSource objectAtIndex:indexPath.row] categoryImage];
    
    
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    self.selectedIndexPath = indexPath;
    [self performSegueWithIdentifier:@"SectionSegue" sender:self];
}

/*
 * segue
 */
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    if ([segue.identifier isEqualToString:@"SectionSegue"]) {
        SectionViewController *sectionController = (SectionViewController *)[segue destinationViewController];
        SourceCategory *source = (SourceCategory *)[self.categoryDataSource objectAtIndex:self.selectedIndexPath.row];
        
        sectionController.sourceCategory = source;
        sectionController.originalDataSource = self.dataSource;
        
    }
    
}
@end
