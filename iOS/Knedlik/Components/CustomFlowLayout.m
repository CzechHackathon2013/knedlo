//
//  CustomFlowLayout.m
//  Knedlo
//
//  Created by Martin Wenisch on 10/19/13.
//  Copyright (c) 2013 Ragtime. All rights reserved.
//

#import "CustomFlowLayout.h"

@implementation CustomFlowLayout

- (UICollectionViewLayoutAttributes *)finalLayoutAttributesForDisappearingItemAtIndexPath:(NSIndexPath *)itemIndexPath
{
    UICollectionViewLayoutAttributes *attributes = [self layoutAttributesForItemAtIndexPath:itemIndexPath];
    attributes.alpha = 0.0;
    if (self.leftDelete) {
        attributes.transform = CGAffineTransformMakeTranslation(-1024, 0);
    } else {
        attributes.transform = CGAffineTransformMakeTranslation(1024, 0);
    }
    
    return attributes;
}

@end
